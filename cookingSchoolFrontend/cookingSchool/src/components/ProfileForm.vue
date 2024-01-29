<script setup>
import { ref, watch } from 'vue'

const props = defineProps(['user', 'showAbort', 'saveText', 'abortText', 'loading'])
const emit = defineEmits(['save', 'abort'])

const userWorkingCopy = ref(createEmptyUserDto())

watch(() => props.user, () => {
  userWorkingCopy.value = Object.assign(createEmptyUserDto(), props.user)
}, {immediate: true})

function save() {
  emit('save', userWorkingCopy.value)
}

function abort() {
  emit('abort')
}

function createEmptyUserDto() {
  return {
    firstname: '',
    lastname: '',
    address: '',
    mobile: '',
    email: '',
    username: '',
    password: '',
    isAdmin: false
  }
}
</script>

<template>
  <v-form @submit.prevent="save">
    <v-row>
      <v-col cols="4">
        <v-text-field label="Vorname" v-model="userWorkingCopy.firstname" required :disabled="loading"/>
      </v-col>
      <v-col cols="4">
        <v-text-field label="Nachname" v-model="userWorkingCopy.lastname" required :disabled="loading"/>
      </v-col>
      <v-col cols="4">
        <v-text-field label="Email" v-model="userWorkingCopy.email" required :disabled="loading"/>
      </v-col>
      <v-col cols="4">
        <v-text-field label="Username" v-model="userWorkingCopy.username" required :disabled="loading"/>
      </v-col>
      <v-col cols="8">
        <v-text-field label="Adresse" v-model="userWorkingCopy.address" required :disabled="loading"/>
      </v-col>
    </v-row>
    <v-btn type="submit" color="primary">{{ saveText || 'Änderungen speichern' }}</v-btn>
    <v-btn class="ml-3" color="secondary" variant="text" @click="abort">{{ abortText || 'Abbrechen' }}</v-btn>
  </v-form>
</template>

