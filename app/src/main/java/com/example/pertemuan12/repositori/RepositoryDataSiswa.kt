package com.example.pertemuan12.repositori

import com.example.pertemuan12.modeldata.DataSiswa

interface RepositoryDataSiswa {
    suspend fun getDataSiswa() : List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa):retrofit2.Response<Void>
    //suspend fun getSatuSiswa(id:Int) : DataSiswa
    //suspend fun editSatuSiswa(id:Int, dataSiswa: DataSiswa):retrofit2.Response<Void>
    //suspend fun hapusSatuSiswa(id:Int):retrofit2.Response<Void>
}