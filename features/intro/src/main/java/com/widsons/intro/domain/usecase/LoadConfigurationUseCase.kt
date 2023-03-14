package com.widsons.intro.domain.usecase

import android.os.Build
import com.widsons.core.data.local.BuildManager
import com.widsons.core.interactor.NoParamUseCase
import com.widsons.intro.data.model.Configuration
import com.widsons.intro.domain.repository.ConfigurationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

typealias SplashLoadConfigurationBaseUseCase = NoParamUseCase<Flow<List<Configuration>>>

@Singleton
class LoadConfigurationUseCase @Inject constructor(
    val configurationRepository: ConfigurationRepository,
    val buildManager: BuildManager
) : SplashLoadConfigurationBaseUseCase {
    override suspend fun invoke() =
        configurationRepository
            .getConfiguration()
            .onEach {
                println("build manager version ${buildManager.buildConfig}")
                if(it.error) {
                    throw Exception(it.message)
                }
                it.details.forEach { itConf ->
                    if(itConf.key == "android_is_maintenance" && itConf.value == "1") {
                        it.details.find { it.key == "android_maintenance_message" }?.let {
                            throw IllegalStateException(it.value)
                        }
                    }
                    if(itConf.key == "android_version" && itConf.value != buildManager.buildConfig) {
                        it.details.find { it.key == "android_update_message" }?.let {
                            throw IllegalStateException(it.value)
                        }
                    }
                }

            }
            .map { it.details }

}
