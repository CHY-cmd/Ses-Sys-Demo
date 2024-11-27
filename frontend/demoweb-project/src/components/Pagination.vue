<template>
  <div class="pagination">
    <button @click="goToPage(modelValue - 1)" :disabled="modelValue === 1"><</button>
    <span v-for="page in displayedPages" :key="page" @click="goToPage(page)" :class="{ active: page === modelValue }">
      {{ page }}
    </span>
    <button @click="goToPage(modelValue + 1)" :disabled="modelValue === totalPages">></button>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    modelValue: {
      type: Number,
      required: true
    },
    totalPages: {
      type: Number,
      required: true
    },
    pageSize: {
      type: Number,
      default: 10
    }
  },
  computed: {
    displayedPages() {
      if (this.totalPages <= 10) {
        return Array.from({ length: this.totalPages }, (_, i) => i + 1);
      } else {
        const pages = [1, 2, 3, 4, 5];
        if (this.totalPages > 10) {
          pages.push('...');
        }
        pages.push(...Array.from({ length: 5 }, (_, i) => this.totalPages - 4 + i));
        const uniquePages = [...new Set(pages)];
        return uniquePages;
      }
    }
  },
  methods: {
    goToPage(page) {
      if (page >= 1 && page <= this.totalPages && page !== '...') {
        this.$emit('update:modelValue', page);
      }
    }
  }
};
</script>

<style scoped>
.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
}

.pagination button, .pagination span {
  margin: 0 5px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.pagination span.active {
  background-color: #9a9a9b;
  color: #fff;
}

.pagination span[aria-disabled="true"] {
  cursor: not-allowed;
}
</style>