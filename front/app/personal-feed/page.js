import Layout from '@/layouts/layout'
import React from 'react'
import dynamic from 'next/dynamic'

const PersonalFeed = dynamic(
  () => {
      return import("@/components/PersonalFeed");
  },
  { ssr: false }
);
export const metadata = {
  title:'Personal Feed',
  content:'text/html',
  openGraph: {
    title:'Intro',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <PersonalFeed />
    </Layout>
  )
}
