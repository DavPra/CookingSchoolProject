<script setup>
import { useRouter } from 'vue-router';
import {onMounted, ref} from 'vue';
import axios from "axios";
import { createApiUrl } from "@/helper/ApiHelper";
import {useUserStore} from "@/stores/userStore.js";
import jwtDecode from "jwt-decode";

const router = useRouter();
const userStore = useUserStore();
const user = ref(null);
const updatedUserData = ref({
  firstname: '',
  lastname: '',
  address: '',
  mobile: '',
  email: '',
  username: '',
  password: '',
  isAdmin: false
})


const updateUserProfile = async () => {
  try {
    // Hier Benutzerdaten aus dem Formular abrufen und updateUser im Store aufrufen
    await userStore.updateUser(/* Benutzerdaten aus dem Formular */);
    // Feedback an den Benutzer geben, dass die Aktualisierung erfolgreich war
  } catch (error) {
    console.error('Fehler beim Aktualisieren des Benutzerprofils', error);
  }
  try {
    // Kopie der aktualisierten Benutzerdaten erstellen, um den Store zu aktualisieren
    const updatedUserCopy = { ...updatedUserData.value };

    // Hier Benutzerdaten aus dem Formular abrufen und updateUser im Store aufrufen
    await userStore.updateUser(updatedUserCopy);
  } catch (error) {
    console.error('Fehler beim Aktualisieren des Benutzerprofils', error);
  }
};

// Ladet Benutzerdaten beim Komponentenstart
onMounted(async () => {
  const token = window.localStorage.getItem('accessToken');
  const decodedToken = jwtDecode(window.localStorage.getItem('accessToken', token));
  const id = decodedToken.userId;
  await userStore.getUser(id);
  user.value = userStore.user;

  // Initialisiert das updatedUserData-Objekt mit den aktuellen Benutzerdaten
  Object.assign(updatedUserData.value, userStore.user);
});



async function updateUser() {
  try {
    //TODO: Kathy - save user data into backend
    const userData = {
      firstname: firstname.value,
      lastname: lastname.value,
      address: address.value,
      mobile: mobile.value,
      email: email.value,
      username: username.value,
      password: password.value,
      isAdmin: isAdmin.value  //has to be false
    };
    console.log(userData);
    try {
      //TODO: Kathy - get userID from accessToken
      const userID = localStorage.getItem('userID');
      //TODO: Kathy - implement userId into path
      const response = await axios.put(createApiUrl('/users/:userId'), userData, {
        headers: {
          //TODO: Kathy - put accessToken into header
          'Content-Type': 'application/json'
        }
      });
      if (response.status === 200) {
        console.log('User updated:', userData);
        await router.push('/user/profile');
      }
    } catch (error) {
      console.error('Fehler beim Updaten des Users:', error);
    }
  } catch (error) {
    console.error('Fehler beim Updaten:', error);
  }
}


//TODO: Kathy - v-text-field should be filled with user data
//TODO: Kathy - password should be hidden characters
</script>

<template>
  <!-- User kann sich seine Daten ansehen/bearbeiten und welche Kurse er bereits gebucht hat -->

  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card>
          <v-card-title class="headline">Profilansicht</v-card-title>

          <v-card-text v-if="user">
            <v-row>
              <v-col cols="12">
                <v-list-subheader>Benutzername: {{ user.username }}</v-list-subheader>
              </v-col>

              <v-col cols="6">
                <v-list-subheader>Vorname: {{ user.firstname }}</v-list-subheader>
              </v-col>

              <v-col cols="6">
                <v-list-subheader>Nachname: {{ user.lastname }}</v-list-subheader>
              </v-col>

              <v-col cols="12">
                <v-list-subheader>Adresse: {{ user.address }}</v-list-subheader>
              </v-col>

              <v-col cols="12">
                <v-list-subheader>Mobil: {{ user.mobile }}</v-list-subheader>
              </v-col>

              <v-col cols="12">
                <v-list-subheader>E-Mail: {{ user.email }}</v-list-subheader>
              </v-col>

              <v-col cols="12">
                <v-list-subheader>Admin: {{ user.isAdmin ? 'Ja' : 'Nein' }}</v-list-subheader>
              </v-col>

              <v-col cols="12">
                <v-form @submit.prevent="updateUserProfile">
                  <v-row>
                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.firstname" label="Vorname" />
                    </v-col>

                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.lastname" label="Nachname" />
                    </v-col>

                    <v-col cols="12">
                      <v-text-field v-model="updatedUserData.address" label="Adresse" />
                    </v-col>

                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.mobile" label="Mobil" />
                    </v-col>

                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.email" label="E-Mail" />
                    </v-col>

                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.username" label="E-Mail" />
                    </v-col>

                    <v-col cols="12" md="6">
                      <v-text-field v-model="updatedUserData.password" label="E-Mail" />
                    </v-col>

                    <v-col cols="12">
                      <v-btn type="submit" color="primary">Änderungen speichern</v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
