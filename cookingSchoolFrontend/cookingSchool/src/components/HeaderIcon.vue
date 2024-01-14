<script setup>
</script>

<template>
    <header class="header">
    <img alt="CookingSchool logo" class="logo" src="@/assets/CookingAcademyBanner.svg"/>
  </header>
</template>

<style scoped>
.header {
  width: 100%;
}

.logo {
  width: 100%; /* Ensure the image fills the container width */
  height: 200px; /* Set the desired height */
  overflow: hidden; /* Hide any overflow */
  object-fit: cover; /* Fills the container proportionally */
  margin:0;
  display: flex;
}
</style>