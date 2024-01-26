<script setup>
import { useRouter } from 'vue-router';
import {ref, defineProps, onMounted} from 'vue';
import {useUserStore} from "@/stores/UserStore";

const router = useRouter();
const courseId = defineProps(['courseId']);
console.log(courseId)
const dialog = ref(false);
const selectedUser = ref(null);
const userStore = useUserStore()
const userIdInput = ref('');
const courseIdValue = courseId.courseId;
console.log(courseIdValue);

onMounted(() => {
  userStore.showUsers();
});
const openDialog = () => {
  dialog.value = true;
};

const closeDialog = () => {
  dialog.value = false;
};

const closeAdminCourseUserView = () => {
  router.go(-1);
};

const addUserToCourse = () => {

  const userId = userIdInput.value.trim();

  if (userId) {
    try {
      userStore.addUserToCourse(userId, courseIdValue);
      console.log(courseIdValue)
      console.log(`User ${userId} added to course ${courseId}`);
      closeAdminCourseUserView();
    } catch (error) {
      console.error(error);
    }
  } else {
    console.warn('No user selected.');
  }
};

</script>

<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-text-field v-model="userIdInput" label="User ID" outlined></v-text-field>
        <v-btn @click="addUserToCourse">Add User</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>



<style scoped>

</style>