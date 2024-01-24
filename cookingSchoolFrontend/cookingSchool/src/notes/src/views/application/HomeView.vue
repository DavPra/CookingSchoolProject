<template>
  <VContainer>
    <VRow>
      <VCol sm="6" md="4" v-for="song in songStore.songs">
        <SongCard :song="song"/>
      </VCol>
    </VRow>
    <span class="text-disabled text-h2 d-block text-center" v-if="!songStore.songs.length">Keine Lieder vorhanden</span>
  </VContainer>
</template>

<script setup>
  import { useSongStore } from '@/store/songStore'
  import { onMounted } from 'vue'
  import SongCard from '@/components/SongCard.vue'

  const songStore = useSongStore()

  onMounted(() => songStore.loadAllSongs())
</script>
