<script setup>
import { ref, watch } from 'vue'

const props = defineProps(['song', 'showAbort', 'saveText', 'abortText', 'loading'])
const emit = defineEmits(['save', 'abort'])

const songWorkingCopy = ref(createEmptySongDto())

watch(() => props.song, () => {
  songWorkingCopy.value = Object.assign(createEmptySongDto(), props.song)
}, {immediate: true})

function save() {
  emit('save', songWorkingCopy.value)
}

function abort() {
  emit('abort')
}

function createEmptySongDto() {
  return {
    songName: '',
    artistName: '',
    releaseDate: ''
  }
}
</script>


<template>
  <form @submit.prevent="save">
    <VRow>
      <VCol cols="4">
        <VTextField label="Liedname" v-model="songWorkingCopy.songName" required :disabled="loading"/>
      </VCol>
      <VCol cols="4">
        <VTextField label="Künstlername" v-model="songWorkingCopy.artistName" required :disabled="loading"/>
      </VCol>
      <VCol cols="4">
        <VTextField type="date" label="Erscheinungsdatum" v-model="songWorkingCopy.releaseDate" required :disabled="loading"/>
      </VCol>
    </VRow>
    <VBtn type="submit" color="primary">{{ saveText || 'Speichern' }}</VBtn>
    <VBtn class="ml-3" color="secondary" variant="text" v-if="showAbort" @click="abort">{{ abortText || 'Abbrechen' }}</VBtn>
  </form>
</template>