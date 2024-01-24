<template>
  <VCardTitle class="pt-4">Login</VCardTitle>
  <form @submit.prevent="login">
    <VCardItem>
      <VAlert color="error" v-model="areCredentialsInvalid" closable close-icon="mdi-close" text="Zugangsdaten falsch" class="mb-6"/>
      <VTextField variant="outlined" required label="Benutzername" v-model="credentialsDto.username" :disabled="isLoginInProgress"/>
      <VTextField type="password" variant="outlined" required label="Fake Passwort" v-model="credentialsDto.password" persistentHint hint="Keine echten Passwörter angeben!" :disabled="isLoginInProgress"/>
    </VCardItem>
    <VCardActions>
      <VBtn color="primary" type="submit" variant="elevated" :loading="isLoginInProgress">Login</VBtn>
      <VBtn color="secondary" to="/register" :disabled="isLoginInProgress">Registrieren</VBtn>
    </VCardActions>
  </form>
</template>

<script setup>
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  import { useAuthenticationStore } from '@/store/authenticationStore'

  const credentialsDto = ref({
    username: '',
    password: ''
  })
  const areCredentialsInvalid = ref(false)
  const isLoginInProgress = ref(false)
  const router = useRouter()
  const authenticationStore = useAuthenticationStore()

  async function login() {
    isLoginInProgress.value = true
    try {
      await authenticationStore.login(credentialsDto.value)
      await router.push('/dashboard')
    } catch (err) {
      areCredentialsInvalid.value = true
    } finally {
      isLoginInProgress.value = false
    }
  }
</script>
