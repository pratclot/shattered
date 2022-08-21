package com.pratclot.di

import com.pratclot.Secrets
import com.pratclot.common.secrets.SecretKeys
import com.pratclot.common.secrets.SecretsProvider
import javax.inject.Inject

const val SECRETS_PACKAGE_NAME = "com.pratclot"

class SecretsProviderActual @Inject constructor() : SecretsProvider {
    override fun getSecretString(key: SecretKeys): String = with(Secrets()) {
        when (key) {
            SecretKeys.API_SECRET_NEWS -> getAPI_SECRET_NEWS(SECRETS_PACKAGE_NAME)
        }
    }
}
