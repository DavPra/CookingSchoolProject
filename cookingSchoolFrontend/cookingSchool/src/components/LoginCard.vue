<!-- Wiederverwendbares Formular fürs Login für Admin und User für die Layout-Navbar -->

<script setup>
    import {ref} from 'vue'
    import {useRouter} from 'vue-router'
    import {useAuthStore} from '@/stores/AuthStore'

    const areCredentialsInvalid = ref(false)
    const isLoginInProgress = ref(false)
    const router = useRouter()
    const authentication = useAuthStore()
    const credentials = ref({
        username: '',
        password: ''
    })

    async function login() {
        isLoginInProgress.value = true
        try {
            await authentication.login(credentials.value)
            await router.push('/courses')
        } catch (err) {
            areCredentialsInvalid.value = true
        } finally {
            isLoginInProgress.value = false
        }
    }
</script>

<template>
    <!-- Wiederverwendbares Formular fürs Login für Admin und User für die Layout-Navbar -->
    <h2>Login</h2>
    <form @submit.prevent="login">
        <div v-if="areCredentialsInvalid" class="error">Zugangsdaten falsch</div>
        <input type="text" required placeholder="username" v-model="credentials.username" :disabled="isLoginInProgress">
        <input type="password" required placeholder="passwort" v-model="credentials.password" :disabled="isLoginInProgress">
        <button type="submit" :disabled="isLoginInProgress">Login</button>
        <br>
        <a href="/register">Registrieren</a>
    </form>
</template>

<style scoped></style>