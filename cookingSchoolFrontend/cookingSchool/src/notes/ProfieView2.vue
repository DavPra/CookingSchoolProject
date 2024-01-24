<script setup>
import { useRouteParams } from '@vueuse/router'
import { useSongStore } from '@/store/songStore'
import { computed, onMounted, ref } from 'vue'
import SongForm from '@/components/SongForm.vue'
import { useRouter } from 'vue-router'
import { useDateFormat } from '@vueuse/core'

const songId = useRouteParams('songId')
const songStore = useSongStore()
const song = computed(() => songStore.songs.find(song => song._id === songId.value))
const showEditDialog = ref(false)
const isSongActionInProgress = ref(false)
const router = useRouter()
const releaseDate = useDateFormat(song.releaseDate, 'DD.MM.YYYY')

onMounted(() => songStore.ensureSong(songId.value))

async function updateSong(updatedSongDto) {
  isSongActionInProgress.value = true

  try {
    await songStore.updateSong(songId.value, updatedSongDto)
    showEditDialog.value = false
  } catch (err) {
    // Im Idealfall alert wie bei NewSongView.vue anzeigen
    console.error(err)
  } finally {
    isSongActionInProgress.value = false
  }
}

async function deleteSong() {
  const deleteConfirmed = confirm('Sind Sie sicher?')
  if(!deleteConfirmed) {
    return
  }
  isSongActionInProgress.value = true
  try {
    await songStore.deleteSong(songId.value)
    await router.replace('/')
  } catch (err) {
    // Im Idealfall alert wie bei NewSongView.vue anzeigen
    console.error(err)
  } finally {
    isSongActionInProgress.value = false
  }
}
</script>


<template>
  <VContainer>
    <div v-if="song">
      <h1>{{ song.songName }}</h1>

      <div>
        <VBtn color="warning" class="mr-4" @click="showEditDialog=true" :disabled="isSongActionInProgress">Bearbeiten</VBtn>
        <VBtn color="error" @click="deleteSong" :disabled="isSongActionInProgress">Löschen</VBtn>
      </div>

      <VDivider class="my-5"/>

      Künstler: <strong>{{song.artistName}}</strong><br>
      Erschienen am: <strong>{{ releaseDate}}</strong>

      <VDialog v-model="showEditDialog" maxWidth="600">
        <VCard>
          <VCardTitle>Lied bearbeiten</VCardTitle>
          <VCardItem class="pb-5">
            <SongForm :song="song" @save="updateSong" @abort="showEditDialog=false" :showAbort="true" :loading="isSongActionInProgress"/>
          </VCardItem>
        </VCard>
      </VDialog>
    </div>
    <span class="d-block text-h1 text-disabled text-center" v-else>Song wird geladen</span>
  </VContainer>
</template>
