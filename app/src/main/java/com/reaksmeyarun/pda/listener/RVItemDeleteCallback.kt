package com.reaksmeyarun.pda.listener

interface RVItemDeleteCallback<ANY> {
    fun onDelete(item: ANY,pos: Int)
}