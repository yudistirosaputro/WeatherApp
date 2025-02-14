package com.yudistiro.data.helper

interface DomainMapper<DataModel, DomainModel> {

    fun mapToDomainModel(dataModel: DataModel): DomainModel

}