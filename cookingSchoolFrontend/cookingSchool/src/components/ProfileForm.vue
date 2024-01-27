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
    <VRow>
      <V-col cols="4">
        <VTextField label="Vorname" v-model="userWorkingCopy.firstname" required :disabled="loading"/>
      </V-col>
      <V-col cols="4">
        <VTextField label="Nachname" v-model="userWorkingCopy.lastname" required :disabled="loading"/>
      </V-col>
      <V-col cols="4">
        <VTextField label="Email" v-model="userWorkingCopy.email" required :disabled="loading"/>
      </V-col>
      <V-col cols="4">
        <VTextField label="Username" v-model="userWorkingCopy.username" required :disabled="loading"/>
      </V-col>
      <V-col cols="4">
        <VTextField label="Adresse" v-model="userWorkingCopy.address" required :disabled="loading"/>
      </V-col>
    </VRow>
    <VBtn type="submit" color="primary">{{ saveText || 'Änderungen speichern' }}</VBtn>
    <VBtn class="ml-3" color="secondary" variant="text" v-if="showAbort" @click="abort">{{ abortText || 'Abbrechen' }}</VBtn>
  </v-form>
</template>

