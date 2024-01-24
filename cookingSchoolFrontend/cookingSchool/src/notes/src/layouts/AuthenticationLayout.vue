<template>
  <VMain class="background">
    <VCard maxWidth="500" minWidth="300">
      <RouterView/>
    </VCard>
  </VMain>
</template>

<style scoped>
  .background {
    display: flex !important;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: relative;
    z-index: 0;
  }
  .background::before {
    display: block;
    position: absolute;
    content: '';
    inset: 0;
    z-index: -1;
    filter: blur(2px) brightness(50%);
    background: url("https://images.pexels.com/photos/267507/pexels-photo-267507.jpeg?auto=compress&cs=tinysrgb&w=1920&h=1080&dpr=1") no-repeat center center;
    background-size: cover;
  }
</style>
