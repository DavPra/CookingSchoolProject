<template>
  <VCard>
    <VCardTitle>{{song.songName}}</VCardTitle>
    <VCardText class="card-body">
      <VDivider class="mb-3"/>
      Künstler: <strong>{{song.artistName}}</strong><br>
      Erschienen am: <strong>{{releaseDate}}</strong>
      <VDivider class="my-3"/>
      <VBtn color="primary" :to="'/song/' + song._id" class="btn btn-primary btn-sm">Detailansicht</VBtn>
    </VCardText>
  </VCard>
</template>

<script setup>
  import { useDateFormat } from '@vueuse/core'

  const props = defineProps(['song'])
  const releaseDate = useDateFormat(props.song.releaseDate, 'DD.MM.YYYY')
</script>
