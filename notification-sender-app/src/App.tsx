import { useEffect, useState } from 'react'
import axios from 'axios'
import SwitchSelector from '../node_modules/react-switch-selector'
import './App.css'

function App() {
  const [isLoading, setIsLoading] = useState(false)
  const [selectedCategory, setSelectedCategory] = useState<String | unknown>()

  const categoryOptions = [
    { label: 'Films', value: 'Films' },
    { label: 'Finance', value: 'Finance'},
    { label: 'Sports', value: 'Sports' },
  ]
  const initialSelectedCategoryIndex = 1

  useEffect(() => {
    setSelectedCategory(categoryOptions[initialSelectedCategoryIndex].value)
  }, [])

  const sendNotification = async () => {
    setIsLoading(true)
    const payload = { category: selectedCategory, message: 'Someone won!' }
    await axios.post('http://localhost:8080/notification/send', payload)
    setIsLoading(false)
  }

  return (
    <>{isLoading && "Loading"}
      <h1>Notification Sender</h1>
      <div className="card">
        <div style={{ width: 300, height: 40, margin: 'auto', marginBottom: '3rem' }}>
            <SwitchSelector
                onChange={setSelectedCategory}
                options={categoryOptions}
                initialSelectedIndex={initialSelectedCategoryIndex}
                backgroundColor={"#353b48"}
                fontColor={"#f5f6fa"}
            />
        </div>
        <button onClick={sendNotification}>
          Sendee
        </button>
      </div>
    </>
  )
}

export default App
