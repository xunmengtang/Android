package com.reaksmeyarun.pda.listener

interface RVItemClickCallback<ANY> {
    fun onClick(item: ANY,pos: Int)
}