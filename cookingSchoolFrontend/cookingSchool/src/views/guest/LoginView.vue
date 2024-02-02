<script setup>
  import {ref} from 'vue';
  import {useRouter} from 'vue-router';
  import {useAuthStore} from '@/stores/AuthStore';
  import jwtDecode from 'jwt-decode';
  import {globalColors as __colors} from "@/assets/colors";

  const areCredentialsInvalid = ref(false);
  const isLoginInProgress = ref(false);
  const router = useRouter();
  const authentication = useAuthStore();
  const credentials = ref({
    username: '',
    password: ''
  });

async function login() {
  try {
    isLoginInProgress.value = true;
    await authentication.login(credentials.value);
    if (isAdmin() === true) {
      await router.push('/admin');
    } else {
      await router.push('/user/courses');
    }
  } catch (error) {
    areCredentialsInvalid.value = true;
    console.error('Fehler beim Login:', error);
  } finally {
    isLoginInProgress.value = false;
  }
}

function isAdmin() {
  const token = localStorage.getItem('accessToken');
  const decodedToken = jwtDecode(localStorage.getItem('accessToken', token))
  if (decodedToken.roles === 'ADMIN') {
    return true;
  } else {
    return false;
  }
}
</script>

<template>
  <v-container class="container">
    <v-col cols="4">
      <v-card>
        <!-- Formular für einen Guest um sich als User einzuloggen, wenn er sich zuvor registriert hat-->

        <v-card-title class="pt-4">Login</v-card-title>
        <v-form @submit.prevent="login">
          <v-card-item>
            <v-alert color="error" v-model="areCredentialsInvalid" closable=true close-icon="mdi-close" text="Zugangsdaten falsch" class="mb-6"/>
            <v-text-field variant="outlined" required label="Benutzername" v-model="credentials.username" :disabled="isLoginInProgress"/>
            <v-text-field type="password" variant="outlined" required label="Passwort" v-model="credentials.password" :disabled="isLoginInProgress"/>
          </v-card-item>
          <v-card-actions style="margin-left: 10px">
            <v-btn color="primary lighten-2" type="submit" rounded variant="elevated" :loading ="isLoginInProgress">Login</v-btn>
          </v-card-actions>
          <v-divider class="my-2"/>
          <v-card-item>
            <div>Noch keinen Account?</div>
          </v-card-item>
          <v-card-item>
            <v-btn color="secondary" rounded variant="elevated" to="/register" class="mb-2" >Hier registrieren</v-btn>
          </v-card-item>
        </v-form>
      </v-card>
    </v-col>
  </v-container>
</template>

<style scoped>
.container{
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
}
</style>
