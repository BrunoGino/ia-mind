import StudentForm from '@/components/StudentForm'
import Layout from '@/layouts/layout'
import React from 'react'

export const metadata = {
  title:'StudentForm',
  content:'text/html',
  openGraph: {
    title:'StudentForm',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <StudentForm />
    </Layout>
  )
}
