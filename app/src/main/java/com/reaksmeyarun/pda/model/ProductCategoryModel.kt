package com.reaksmeyarun.pda.model

data class ProductCategoryModel(var id : String? = "",
                           var categoryModel: CategoryModel,
                           var quantities : String ?= "",
                           var timestamp : String ?= "",
                           var description : String ?= "")