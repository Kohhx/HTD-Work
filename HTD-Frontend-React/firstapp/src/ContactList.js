import React from 'react'
import Contact from './Contact'

function ContactList() {
  return <div style={{display:"flex",gap:"5px"}}>
    <Contact name="James" message="Good morning" image="https://sm.ign.com/ign_ap/cover/a/avatar-gen/avatar-generations_hugw.jpg"/>
    <Contact name="Leon" message="Good morning" image="https://img.lovepik.com/element/40128/7461.png_1200.png"/>
    <Contact name="Sabrina" message="Good morning" image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_CQ3IrjZcisW-FO12jxRtSA9shZYuykqA2w&usqp=CAU"/>
  </div>
}

export default ContactList
