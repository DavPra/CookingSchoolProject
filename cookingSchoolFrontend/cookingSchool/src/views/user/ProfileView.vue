<script setup>
import ProfileForm from '@/components/ProfileForm.vue';
import {ref, computed, onUpdated, onMounted} from 'vue';
import {useRouter} from 'vue-router';
import {useUserStore} from '@/stores/userStore.js';
import {useAuthStore} from '@/stores/authStore.js';

const userStore = useUserStore();
const authStore = useAuthStore();
const userId = computed(() => authStore.getUserId());
const user = computed(async () => {
  try {
    return await userStore.findUser(userId.value)
  } catch (err) {
    console.error(err.message)
  }
})

const showEditDialog = ref(false);
const isProfileActionInProgress = ref(false);
const router = useRouter();

async function updateUser(updatedUserDto) {
  isProfileActionInProgress.value = true;
  try {
    await userStore.updateUser(userId.value, updatedUserDto)
    showEditDialog.value = false;
  } catch (err) {
    console.error(err)
  } finally {
    isProfileActionInProgress.value = false;
  }
}

onMounted(async () => {
  try {
    await userStore.findUser(userId.value)
  } catch (err) {
    console.error(err.message)
  }
})

const showUser = ref({
  firstname: user.value.firstname,
  lastname: user.value.lastname,
  email: user.value.email,
  username: user.value.username
})
console.log(showUser.value)

const userToEdit = (user) => {
  userToEdit.value = user;
  }

  onUpdated(() => {
    showUser.value = {
      firstname: user.value.firstname,
      lastname: user.value.lastname,
      email: user.value.email,
      username: user.value.username
    }
  })

</script>

<template>
  <!-- User kann sich seine Daten ansehen/bearbeiten/löschen -->

  <v-container>
    <v-card v-if="user">
      <v-card-title>{{ showUser.username }}</v-card-title>
      <v-card-item>
        <v-card-text>Vorname: {{ showUser.firstname }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Nachname: {{ showUser.lastname }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Email: {{ showUser.email }}</v-card-text>
      </v-card-item>
      <v-divider class="my-2"/>
      <v-card-item>
        <v-btn color="primary" class="my-3" @click="showEditDialog=true" :disabled="isProfileActionInProgress">Bearbeiten</v-btn>
      </v-card-item>
      <v-dialog v-model="showEditDialog" max-width="600">
        <v-card>
          <v-card-title>Profil bearbeiten</v-card-title>
          <v-card-item class="pb-5">
            <ProfileForm :user="user" @save="updateUser" @abort="showEditDialog=false" :loading="isProfileActionInProgress"/>
          </v-card-item>
        </v-card>
      </v-dialog>
    </v-card>
    <span class="d-block text-h1 text-disabled text-center" v-else>User wird geladen</span>
  </v-container>

</template>