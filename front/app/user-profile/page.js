import UserProfile from '@/components/UserProfile'
import Layout from '@/layouts/layout'
import React from 'react'

export const metadata = {
  title:'User Profile',
  content:'text/html',
  openGraph: {
    title:'User Profile',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <UserProfile />
    </Layout>
  )
}
