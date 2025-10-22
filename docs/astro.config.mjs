// @ts-check
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

// https://astro.build/config
export default defineConfig({
	site: 'https://SlimifiedxD.github.io',
	base: '/bedrock/',
	integrations: [
		starlight({
			title: 'Bedrock',
			social: [{ icon: 'github', label: 'GitHub', href: 'https://github.com/SlimifiedxD/bedrock' }],
			sidebar: [
				{
					label: 'Introduction',
					autogenerate: { directory: 'introduction' }
				},
				{
					label: 'Cookbook',
					autogenerate: { directory: 'cookbook' }
				}
			],
		}),
	],
});
