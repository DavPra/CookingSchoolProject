<script setup>
import LoginCard from '@/components/LoginCard.vue'
</script>

<template>
  <div class="container">
    <div class="item">
    <h3 class="text">Welcome</h3>
    
    <p class="text">
    This project is served and bundled with Vite. The
    recommended IDE setup is
    VSCode +
    Volar. If
    you need to test your components and web pages, check out Cypress and Cypress Component Testing.
    <br />
    More instructions are available in README.md.
    <br />
    Get official tools and libraries for your project: Pinia, Vue Router, Vue Test Utils, and Vue Dev Tools. 
    If you need more resources, we suggest paying Awesome Vue a visit.
    </p>
    </div>
  <div class="item">
    <LoginCard />
  </div>
  </div>
</template>

<style scoped>
.text {
  margin-top: 2rem;
  display: flex;
  flex: 1;
  margin-left: 1rem;
}

h3 {
  font-size: 1.5rem;
  font-weight: 500;
  margin-bottom: 0.4rem;
  color: black;
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  border: 2px solid black;
  margin-left:1px;
  margin-right:1px;
  text-decoration: none;
}

</style>