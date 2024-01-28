<script setup>
import Logo from "@/components/Logo.vue";
</script>

<template>
  <!-- Welcome page for all users with login option -->
  <div class="container">
    <div class="item">
      <h3 class="centered-heading"> Welcome to the Cooking Academy</h3>
  
      <p class="text-center">
        Discover exciting recipes and sign up for our cooking classes to enhance your culinary skills. 
      </p>
    </div>
  </div>
</template>

<style scoped>
.text {
  margin-top: 1.5rem;
  font-size: 1.2rem;
}

.centered-heading {
  font-size: 2rem;
  font-weight: bold;
  color: #CB5400; /* Orange color for the heading */
  margin-bottom: 1rem;
  text-align: center; /* Center the heading */
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f8f9fa; /* Light gray background */
}

/* Button style */
.v-btn {
  margin-top: 1.5rem;
}

.item {
  width:30%;
}
</style>
