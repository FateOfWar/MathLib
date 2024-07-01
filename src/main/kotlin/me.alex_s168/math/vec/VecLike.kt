package me.alex_s168.math.vec

import java.nio.ByteBuffer
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface VecLike<T, S>: Collection<T> {
    operator fun get(index: Int): T
    operator fun set(index: Int, value: T)

    override fun iterator(): Iterator<T> =
        VecIterator(this)

    class VecIterator<T, S>(val vec: VecLike<T, S>): Iterator<T> {
        var index = 0
        override fun hasNext(): Boolean =
            index < vec.size
        override fun next(): T =
            vec[index++]
    }

    override fun contains(element: T): Boolean {
        for (e in this) {
            if (e == element) {
                return true
            }
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) {
                return false
            }
        }
        return true
    }

    override fun isEmpty(): Boolean =
        false

    fun copy(): S

    fun from(buf: ByteBuffer)
    fun writeTo(buf: ByteBuffer)
}

@OptIn(ExperimentalContracts::class)
inline fun <T, S, R, VS, V: VecLike<R, VS>> VecLike<T, S>.modifyTo(dest: V, each: (T) -> R): V {
    contract {
        callsInPlace(each)
    }

    repeat(size) {
        dest[it] = each(this[it])
    }

    return dest
}

@OptIn(ExperimentalContracts::class)
inline fun <T, S, R, VS, V: VecLike<R, VS>> VecLike<T, S>.modifyWithIndexTo(dest: V, each: (Int, T) -> R): V {
    contract {
        callsInPlace(each)
    }

    repeat(size) {
        dest[it] = each(it, this[it])
    }

    return dest
}

@OptIn(ExperimentalContracts::class)
inline fun <T, S> VecLike<T, S>.modify(each: (T) -> T) {
    contract {
        callsInPlace(each)
    }

    repeat(size) {
        this[it] = each(this[it])
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <T, S> VecLike<T, S>.modifyWithIndex(each: (Int, T) -> T) {
    contract {
        callsInPlace(each)
    }

    repeat(size) {
        this[it] = each(it, this[it])
    }
}

fun <T, S, V: VecLike<T, S>> Iterable<T>.collect(dest: V): V {
    forEachIndexed { index, t ->
        dest[index] = t
    }
    return dest
}

fun <T, S> VecLike<T, S>.fillWith(src: Iterable<T>) {
    src.collect(this)
}