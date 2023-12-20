<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HeaderIcon from '@/components/HeaderIcon.vue'
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Admin -->
    <HeaderIcon/>
    <div class="container">
      <div class="item">
        <nav>
        <RouterLink to="/admin/courses">Kurse</RouterLink>
        </nav>
      </div>
      <div class="item">
        <nav>
        <RouterLink to="/admin/recipes">Rezepte</RouterLink>
        </nav>
      </div>
      <div class="item">
        <nav>
        <RouterLink to="/admin/users">User</RouterLink>
        </nav>
      </div>
      <div class="item">
        <button class="button">
          Login
        </button>
      </div>
    </div>
  <RouterView />
</template>

<style scoped>
.item {
  width: 100%;
  font-size: 1.2rem;
  font-weight: 500;
  text-decoration: none;
  margin-bottom: 0.4rem;
  color: var(--color-text);
  margin-right: 12px;
  margin-left: 12px;
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  border: 2px solid black;
  max-width: 100%;
}

.button {
  background-color: transparent;
  border: none;
  font-size: 1.2rem;
  font-weight: 500;
  text-decoration: none;
  color: var(--color-text);
  padding: 10px 20px;
  text-align: center;
  display: inline-block;
}
</style>