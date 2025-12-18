package pertemuan12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pertemuan12.model.Siswa
import pertemuan12.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<Siswa>) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(private val repositoryDataSiswa: RepositoryDataSiswa):
    ViewModel() {
    var homeUiState: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            homeUiState = StatusUiSiswa.Loading
            homeUiState = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getSiswa())
            } catch (e: IOException) {
                StatusUiSiswa.Error
            } catch (e: HttpException) {
                StatusUiSiswa.Error
            }
        }
    }

    // Tambahan fungsi delete jika diperlukan di UI Home
    fun deleteSiswa(nis: String) {
        viewModelScope.launch {
            try {
                repositoryDataSiswa.deleteSiswa(nis)
                loadSiswa() // Refresh data setelah delete
            } catch (e: Exception) {
                homeUiState = StatusUiSiswa.Error
            }
        }
    }
}