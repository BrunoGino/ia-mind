import Layout from '@/layouts/layout'
import dynamic from 'next/dynamic'
import React from 'react'

export const metadata = {
  title:'Community Feed',
  content:'text/html',
  openGraph: {
    title:'Community Feed',
    content:'text/html',
  },
}

const CommunityFeed = dynamic(
  () => {
      return import("@/components/CommunityFeed");
  },
  { ssr: false }
);
export default function page() {
  return (
    <Layout>
      <CommunityFeed />
    </Layout>
  )
}
