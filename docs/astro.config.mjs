// @ts-check
import { defineConfig } from "astro/config";
import starlight from "@astrojs/starlight";

// https://astro.build/config
export default defineConfig({
  site: "https://SlimifiedxD.github.io",
  base: "/quartz/",
  integrations: [
    starlight({
      title: "Quartz",
      social: [
        {
          icon: "github",
          label: "GitHub",
          href: "https://github.com/SlimifiedxD/quartz",
        },
      ],
      customCss: ["./src/styles/theme.css"],
      sidebar: [
        {
          label: "Introduction",
          autogenerate: { directory: "introduction" },
        },
        {
          label: "Cookbook",
          autogenerate: { directory: "cookbook" },
        },
      ],
    }),
  ],
});
