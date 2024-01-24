<template>
  <VContainer>
    <h1 class="mb-4">New Song</h1>
    <VAlert color="error" closable close-icon="mdi-close" class="mb-4" :modelValue="!!songCreationError" @update:modelValue="songCreationError=''" :text="songCreationError"/>
    <SongForm @save="createSong" :loading="isSongCreationInProgress"/>
  </VContainer>
</template>

<script setup>
  import { ref } from 'vue'
  import { useSongStore } from '@/store/songStore'
  import { useRouter } from 'vue-router'
  import SongForm from '@/components/SongForm.vue'

  const songStore = useSongStore()
  const router = useRouter()
  const songCreationError = ref('')
  const isSongCreationInProgress = ref(false)

  async function createSong(newSongDto) {
    isSongCreationInProgress.value = true

    try {
      const createdSong = await songStore.createSong(newSongDto)
      console.log(createdSong)
      await router.push(`/song/${createdSong._id}`)
    } catch (err) {
      console.error(err)
      songCreationError.value = 'Ein unbekannter Fehler ist aufgetreten.'
    } finally {
      isSongCreationInProgress.value = false
    }
  }
</script>
