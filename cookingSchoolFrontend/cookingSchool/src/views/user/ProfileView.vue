<script setup>
import ProfileForm from '@/components/ProfileForm.vue';
import { ref, onBeforeMount } from 'vue';
import { useUserStoreUpdate } from '@/stores/UserStoreforUpdate';
import jwtDecode from 'jwt-decode';
import axios from 'axios';

const userStore = useUserStoreUpdate();

const showEditDialog = ref(false);
const isProfileActionInProgress = ref(false);
const userId = jwtDecode(localStorage.getItem("accessToken")).userId;

let user = ref(null);

onBeforeMount(async () => {
  await getUserData();
});

async function getUserData() {
  try {
    const response = await axios.get(`http://localhost:8082/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    });
    user.value = response.data;
  } catch (error) {
    console.error('Error fetching user:', error);
  }
}

async function updateUsers(updatedUserDto) {
  isProfileActionInProgress.value = true;
  try {
    await userStore.updateUser(userId, updatedUserDto);
    showEditDialog.value = false;
    await getUserData(); // Update user data after the update
  } catch (err) {
    console.error(err);
  } finally {
    isProfileActionInProgress.value = false;
  }
}

</script>

<template>
  <!-- User kann sich seine Daten ansehen/bearbeiten/löschen -->

  <v-container>
    <h1>Hallo {{ user?.firstname }}</h1>
    <v-divider class="my-2"/>
    <v-container class="container bg-brown-lighten-5 ">
      <v-col cols="6">
        <v-card class="pa-2 ma-2 text-center">
          <v-icon size="48">mdi-account</v-icon>
          <v-card-title>
            Profil
          </v-card-title>
          <v-divider class="my-2"/>
          <v-card-text>Vorname: {{ user?.firstname }}</v-card-text>
          <v-card-text>Nachname: {{ user?.lastname }}</v-card-text>
          <v-card-text>Email: {{ user?.email }}</v-card-text>
          <v-card-text>Benutzername: {{ user?.username }}</v-card-text>
          <v-card-text>Adresse: {{ user?.address }}</v-card-text>
          <v-divider class="my-2"/>
          <v-card-item>
            <v-btn color="primary" class="mr-4" @click="showEditDialog = true"
                   :disabled="isProfileActionInProgress" rounded>
              Bearbeiten
            </v-btn>
          </v-card-item>
        </v-card>
      </v-col>
      <v-dialog v-model="showEditDialog" max-width="600">
        <v-card>
          <v-card-title>Profil bearbeiten</v-card-title>
          <v-card-item class="pb-5">
            <ProfileForm :user="user" @save="updateUsers" @abort="showEditDialog = false" :loading="isProfileActionInProgress"/>
          </v-card-item>
        </v-card>
      </v-dialog>
    </v-container>
  </v-container>

</template>

<style scoped>
.container {
  background-color: #FAFAFA;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
}
</style>