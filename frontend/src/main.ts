import Provider from '@/layouts/Provider.vue';
import { setupRouter } from '@/router';
import { createApp } from 'vue';
import 'virtual:windi.css';
import { setupStore } from '@/store';

const app = createApp(Provider);
setupRouter(app);
setupStore(app);
app.mount('#app');
