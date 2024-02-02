<script setup>
const props = defineProps(['deleteConfirm', 'abortDelete'])
const emit = defineEmits(['delete', 'abort'])

function save() {
  emit('delete')
}

function abort() {
  emit('abort')
}
</script>

<template>
  <v-form @submit.prevent="save" class="form">
    <v-row>
      <v-col>
        <h3>Wollen Sie wirklich löschen?</h3><br>
      </v-col>
    </v-row>
    <v-row class="buttonrow">
      <v-btn type="submit" color="primary" rounded>{{ deleteConfirm || 'Löschen' }}</v-btn>
      <v-btn color="secondary" @click="abort" rounded>{{ abortDelete || 'Abbrechen' }}</v-btn>
    </v-row>
  </v-form>
</template>

<style scoped>

.buttonrow {
  justify-content: space-around;
  margin-bottom: 3px;
}
</style>