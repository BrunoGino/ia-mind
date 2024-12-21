import Privacy from '@/components/Privacy'
import Layout from '@/layouts/layout'
import React from 'react'


export const metadata = {
  title:'Privacy',
  content:'text/html',
  openGraph: {
    title:'Privacy',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <Privacy />
    </Layout>
  )
}
