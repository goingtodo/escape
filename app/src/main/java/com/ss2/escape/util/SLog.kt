package com.ss2.escape.util

import android.util.Log

class SLog {

    companion object {
        var debug : Boolean = true
        fun e(message: String?) {
            if (debug) Log.e(tagLog(), message)
        }

        /** Log Level Warning  */
        fun w(message: String?) {
            if (debug) Log.w(tagLog(), message)
        }

        /** Log Level Information  */
        fun i(message: String?) {
            if (debug) Log.i(tagLog(), message)
        }

        /** Log Level Debug  */
        fun d(message: String?) {
            if (debug) Log.d(tagLog(), message)
        }

        /** Log Level Verbose  */
        fun v(message: String?) {
            if (debug) Log.v(tagLog(), message)
        }

        fun tagLog(): String? {
            val tag = "Escape "
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append(tag)
            sb.append("[")
            sb.append(ste.fileName.replace(".java", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]  ")
            return sb.toString()
        }
    }

}