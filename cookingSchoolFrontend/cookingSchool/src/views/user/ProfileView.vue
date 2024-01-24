<script setup>
import { computed, onMounted, ref } from 'vue';
import ProfileForm from '@/components/ProfileForm.vue';
import { useRouter } from 'vue-router';
import {useUserStore} from "@/stores/userStore.js";
import {useAuthStore} from "@/stores/authStore.js";
import jwtDecode from "jwt-decode";

const token = window.localStorage.getItem('accessToken');
const decodedToken = jwtDecode(window.localStorage.getItem('accessToken', token))
const userId = decodedToken.userId;

const userStore = useUserStore();
const user = computed(() => userStore.users.find(user => user._id === userId.value))
const showEditDialog = ref(false)
const isProfileActionInProgress = ref(false)
const router = useRouter()

onMounted(async () => {
  try {
    const user = await userStore.findUser(userId.value);
    console.log('user Component mounted', user);
  } catch (error) {
    console.error('Error loading users in component mount:', error);
  }
})

async function updateUser(updatedUserDto) {
  isProfileActionInProgress.value = true

  try {
    await userStore.updateUser(userId.value, updatedUserDto)
    showEditDialog.value = false
  } catch (err) {
    // Im Idealfall alert wie bei RegistrationView.vue anzeigen
    console.error(err)
  } finally {
    isProfileActionInProgress.value = false
  }
}
</script>


<template>
  <v-container>
    <div v-if="user">
      <h1>{{ user.username }}</h1>

      <div>
        <v-btn color="warning" class="mr-4" @click="showEditDialog=true" :disabled="isProfileActionInProgress">Bearbeiten</v-btn>
      </div>

      <v-divider class="my-5"/>

      Vorname: <strong>{{user.firstname}}</strong><br>
      Nachname: <strong>{{ user.lastname}}</strong><br>
      Email: <strong>{{ user.email}}</strong>

      <v-dialog v-model="showEditDialog" maxWidth="600">
        <v-card>
          <v-card-title>Profil bearbeiten</v-card-title>
          <v-card-item class="pb-5">
            <ProfileForm :user="user" @save="updateUser" @abort="showEditDialog=false" :showAbort="true" :loading="isProfileActionInProgress"/>
          </v-card-item>
        </v-card>
      </v-dialog>
    </div>
    <span class="d-block text-h1 text-disabled text-center" v-else>User wird geladen</span>
  </v-container>
</template>
