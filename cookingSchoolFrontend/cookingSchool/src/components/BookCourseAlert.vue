<script setup>
const props = defineProps(['bookConfirm', 'abortBook'])
const emit = defineEmits(['confirm', 'abort'])

function save() {
  emit('confirm')
}

function abort() {
  emit('abort')
}
</script>

<template>
  <v-form @submit.prevent="save" class="form">
    <v-row>
      <v-col>
        <h3>Wollen Sie diesen Kurs wirklich buchen?</h3><br>
      </v-col>
    </v-row>
    <v-row class="buttonrow">
      <v-btn type="submit" color="primary" rounded>{{ bookConfirm || 'Bestätigen' }}</v-btn>
      <v-btn color="secondary" @click="abort" rounded>{{ abortBook || 'Abbrechen' }}</v-btn>
    </v-row>
  </v-form>
</template>

<style scoped>

.buttonrow {
  justify-content: space-around;
  margin-bottom: 3px;
}
</style>