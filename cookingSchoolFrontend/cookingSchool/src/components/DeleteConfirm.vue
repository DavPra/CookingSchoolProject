<script setup>
const props = defineProps(['deleteConfirm', 'abortConfirm'])
const emit = defineEmits(['delete', 'abort'])

function save() {
  emit('delete')
}

function abort() {
  emit('abort')
}
</script>

<template>
  <v-form @submit.prevent="save">
    <v-row>
      <v-col cols="8">
        <h2> Wollen Sie wirklich löschen?</h2><br/>
      </v-col>
    </v-row>
    <v-btn type="submit" color="primary">{{ deleteConfirm || 'Löschen' }}Löschen</v-btn>
    <v-btn class="ml-3" color="secondary" variant="text" @click="abort">{{ abortConfirm || 'Abbrechen' }}</v-btn>
  </v-form>
</template>