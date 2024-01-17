<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from "axios";
import { createApiUrl } from "@/helper/ApiHelper";

const router = useRouter();

const firstname = ref('');
const lastname = ref('');
const address = ref('');
const mobile = ref('');
const email = ref('');
const username = ref('');
const password = ref('');
const isAdmin = ref(false);

async function registerUser() {
  try {
    const userData = {
      firstname: firstname.value,
      lastname: lastname.value,
      address: address.value,
      mobile: mobile.value,
      email: email.value,
      username: username.value,
      password: password.value,
      isAdmin: isAdmin.value
    };
    console.log(userData);
    try {
      const response = await axios.post(createApiUrl('/registration'), userData, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.status === 200) {
        console.log('User created:', userData);
        await router.push('/courses');
      }
    } catch (error) {
      console.error('Ein Fehler ist aufgetreten:', error);
    }
  } catch (error) {
    console.error('Fehler beim Hinzufügen des Benutzers:', error);
  }
}
</script>

<template>
  <!-- Formular für einen neuen User um sich zu registrieren -->

  <v-container>
    <v-form @submit.prevent="registerUser">
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="firstname" label="Vorname" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="lastname" label="Nachname" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-text-field v-model="address" label="Adresse" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="mobile" label="Handynummer" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="email" label="E-Mail" type="email" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="username" label="Benutzername" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="password" label="Passwort" type="password" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-btn type="submit" color="primary">Registrieren</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>
