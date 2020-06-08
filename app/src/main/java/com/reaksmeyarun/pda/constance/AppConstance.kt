package com.reaksmeyarun.pda.constance

class AppConstance {
    companion object{
//      User session
        const val USER_TOKEN = "userToken"
//      RequestCode
        const val P0200HOME = 100
        const val P0300INBOX = 200
        const val P0400PROMOTION = 300
        const val Z0200SETTING = 400
        const val Z0300ABOUT_US = 500
//      ResultCode
        const val RESULT_P0200HOME = 101
        const val RESULT_P0300INBOX = 201
        const val RESULT_P0400PROMOTION = 301
        const val RESULT_Z0200SETTING = 401
        const val RESULT_Z0300ABOUT_US = 501
//      Quantities
        const val ADD_TO_CART = 1
        const val EDIT_ON_CART = 2
//      Node firebase table
        const val USER_NODE = "user"
        const val CATEGORY_NODE = "category"
        const val CATEGORY_INFORMATION_NODE = "categoryInformation"
        const val ITEM_NODE = "item"
        const val ITEM_INFORMATION_NODE = "itemInformation"
//      Constance firebase listener
        const val ON_SUCCESS_LISTENER = "OnSuccessListener"
        const val ON_FAILURE_LISTENER = "OnFailureListener"
        const val ON_CANCEL_LISTENER = "OnCancelListener"
        const val ON_COMPLETE_LISTENER = "OnCompleteListener"
        const val ON_CHILD_MOVED = "onChildMoved"
        const val ON_CHILD_CHANGED = "onChildChanged"
        const val ON_CHILD_ADD = "onChildAdded"
        const val ON_CHILD_REMOVED = "onChildRemoved"
        const val ON_DATA_CHANGED = "onDataChanged"
//      Exception
        const val EXCEPTION = "Exception"
        const val EXCEPTION_IN_INITIALIZER_ERROR = "ExceptionInInitializerError"
        const val FINALLY = "Finally"
        const val DATABASE_ERROR = "DatabaseError"
        const val VIEW_MODEL = "ViewModel"
//      Url
        const val NO_IMAGE_URL = "https://firebasestorage.googleapis.com/v0/b/point-on-sale.appspot.com/o/No_image_available.svg.png?alt=media&token=32dbfe5c-bba3-4369-9804-de5e0f499c27"
//      status
        const val CAN_NOT_DELETE = 0
        const val CAN_DELETE = 1

            const val DELETE_LISTENER = "onDeleteListener"
            const val EDIT_LISTENER = "onEditListener"
            const val CLICK_LISTENER = "onClickListener"
    }
}