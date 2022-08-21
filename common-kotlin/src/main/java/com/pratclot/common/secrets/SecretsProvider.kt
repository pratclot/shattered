package com.pratclot.common.secrets

interface SecretsProvider {
    fun getSecretString(key: SecretKeys): String
}
