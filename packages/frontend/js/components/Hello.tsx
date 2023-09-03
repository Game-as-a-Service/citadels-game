// src/components/Hello.tsx

import React from 'react'

interface Props {
  name: string
}

const Hello: React.FC<Props> = ({ name }) => {
  return (
    <div>
      <h1>Hello, {name}!</h1>
    </div>
  )
}

export default Hello
