<script setup>
import LoginCard from '@/components/LoginCard.vue'
</script>

<template>
  <!-- Willkommensseite für alle User mit Login-Möglichkeit -->
  <div class="container">
    <div class="item">
    
    <p class="text">
    Willkommen zur Cooking Academy. Hier finden Sie viele tolle Rezepte und können sich für unsere Kochkurse anmelden.
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