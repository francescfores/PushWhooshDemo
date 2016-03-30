<template>
  <div id="app" class="contanier">
  	  <div class="col col-md-8 col-md-offset-2">
      <pushwoosh></pushwoosh>
     </div>
  </div>
</template>

<script>
import pushwoosh from './components/pushwoosh.vue'

export default {
  components: {
    pushwoosh
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
