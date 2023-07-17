import { useEffect, useState } from 'react'
import axios from 'axios'
import SwitchSelector from '../node_modules/react-switch-selector'
import Button from 'react-bootstrap/Button'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import ListGroup from 'react-bootstrap/ListGroup'
import Placeholder from 'react-bootstrap/Placeholder'
import Row from 'react-bootstrap/Row'
import Spinner from 'react-bootstrap/Spinner'
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import ToastMessage from './components/ToastMessage'

function App() {
  const [isSendMessageProcessing, setIsSendMessageProcessing] = useState(false)
  const [isLogListLoading, setIsLogListLoading] = useState(false)
  const [isCategoryOptionsLoading, setIsCategoryOptionsLoading] = useState(false)
  const [categoryOptions, setCategoryOptions] = useState<any[]>([])
  const [selectedCategory, setSelectedCategory] = useState<String | unknown>()
  const [notificationMessage, setNotificationMessage] = useState<String>()
  const [notificationLogList, setNotificationList] = useState<any[]>([])
  const [isSuccessMessageVisible, setIsSuccessMessageVisible] = useState<boolean>(false)
  const [successMessage, setSuccessMessage] = useState<string>('')

  useEffect(() => {
    loadCategoryOptions()
    loadNotificationLogList()
  }, [])

  const loadCategoryOptions = async () => {
    try {
      setIsCategoryOptionsLoading(true)
      const response = await axios.get('http://localhost:8080/notification/get-categories')
      const categoryOptionsResponse = response.data.map((category: any) => ({ label: category.name, value: category.name }))
      setCategoryOptions(categoryOptionsResponse)
      categoryOptionsResponse?.[0] && setSelectedCategory(categoryOptionsResponse[0].value)
    } catch(error: any) {
      console.error(error.message)
    } finally {
      setIsCategoryOptionsLoading(false)
    }
  }

  const sendNotification = async () => {
    setIsSendMessageProcessing(true)
    try {
      const payload = { category: selectedCategory, message: notificationMessage }
      const response = await axios.post('http://localhost:8080/notification/send', payload)
      showSuccessMessage(response.data.message)
      loadNotificationLogList()
    } catch(error: any) {
      console.error(error.response.data.message || error.message)
    } finally {
      setIsSendMessageProcessing(false)
    }
  }

  const loadNotificationLogList = async () => {
    try {
      setIsLogListLoading(true)
      const response = await axios.get('http://localhost:8080/notification/get-notification-log-history?language=' + window.navigator.language)
      setNotificationList(response.data)
    } catch(error: any) {
      console.error(error.message)
    } finally {
      setIsLogListLoading(false)
    }
  }

  const showSuccessMessage = (message: string) => {
    setIsSuccessMessageVisible(true)
    setSuccessMessage(message)
  }

  return (
    <Container data-bs-theme="dark">
      <h1 className='text-center mb-5 mt-5'>Notification Sender</h1>
      <Row className='justify-content-center'>
        <Col sm={3}  md={2} lg={1} className='text-start mb-2'>
          <label>Category</label>
        </Col>
        <Col sm={9} md={6} lg={6}>
          {isCategoryOptionsLoading ?
            <Placeholder as={ListGroup.Item} animation="glow"><Placeholder sm={6}></Placeholder></Placeholder> :
            <div className='mb-3' style={{ width: 300, height: 40 }}>
              <SwitchSelector
                  onChange={setSelectedCategory}
                  options={categoryOptions}
                  backgroundColor={"#353b48"}
                  fontColor={"#f5f6fa"} />
            </div>
          }
        </Col>
      </Row>
      <Row className='justify-content-center'>
        <Col sm={3} md={2} lg={1} className='text-start mb-2'>
          <label>Message</label>
        </Col>
        <Col sm={9} md={6} lg={6}>
          <Form>
          <Form.Control as='textarea' style={{ height: '100px' }}
              onChange={e => setNotificationMessage(e.target.value)}/>
          </Form>
        </Col>
      </Row>
      <Row className='justify-content-center mt-3'>
        <Col sm={3}  md={2} lg={1} />
        <Col sm={9} md={6} lg={6}>
          <Button onClick={sendNotification} disabled={isSendMessageProcessing || !notificationMessage?.trim()} style={{ width: '10rem' }}>
            {isSendMessageProcessing ? <Spinner animation="border" size="sm" /> : 'Send Notification'}
          </Button>
        </Col>
      </Row>
      <div style={{ margin: 'auto', marginTop: '3rem' }}>
        {(isLogListLoading || notificationLogList.length > 0) ?
          <label className='mb-2'>Log History</label> : 'The log is empty.'
        }
        <ListGroup className='mb-5'>
          {isLogListLoading && !notificationLogList.length ?
            [...Array(10).keys()].map(index => <Placeholder key={index} as={ListGroup.Item} animation="glow"><Placeholder sm={6}></Placeholder></Placeholder>) :
            notificationLogList.map(notification => <ListGroup.Item id='log-line' key={notification.id} action>{notification.logText}</ListGroup.Item>)
          }
        </ListGroup>
      </div>
      <ToastMessage show={isSuccessMessageVisible} message={successMessage} onClose={() => setIsSuccessMessageVisible(false)} />
    </Container>
  )
}

export default App
