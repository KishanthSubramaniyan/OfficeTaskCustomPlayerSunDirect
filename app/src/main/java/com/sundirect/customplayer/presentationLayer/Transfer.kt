package com.sundirect.customplayer.presentationLayer

class Transfer {

    interface Alpha<A>
    {
        fun action(alpha : A)
    }

    interface Beta<A,B>
    {
        fun action(alpha: A, beta: B)
    }

    interface Gamma<A,B,G>
    {
        fun action(alpha : A, beta : B, gamma : G)
    }
}