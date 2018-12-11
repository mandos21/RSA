# RSA 

RSA (Rivest–Shamir–Adleman) Encryption Implementation made in Java

## Documentation

---->  Directions http://cs.rowan.edu/~bergmann/crypto/projects/project3/project3.html

----> RSA Class API https://mandos21.github.io/RSA/RSA.html

----> Person Class API https://mandos21.github.io/RSA/Person.html

## Session Transcript

Message is: Régime, dōjō, Müller, jalapeño, jäger, naïve, shōgun

Alice sends:

[23774873959284577, 7891477046851858, 22780884016486961, 24832368427793212, 8684414438310399, 7939282414593589, 24832368427793212, 19349032310265823, 4728407273952390, 3555842381845187, 24832368427793212, 11321762037053149, 20558739661642103, 28214717285798487, 22223888257211114, 24832368427793212, 12654290675685545, 11691601811658866, 10681003730637624, 6491360497072473, 25203684179540162, 5172827319045819, 24832368427793212, 15230707522748431, 5746542781169698, 10722873774215888]

Bob decodes and reads: Régime, dōjō, Müller, jalapeño, jäger, naïve, shōgun

Message is: What are you talking about Alice?

Bob sends:

[29177122700796833, 15961247669858324, 13114195683776624, 29392445712539846, 17679503525230393, 16812537127621119, 30818239871546970, 3012100906018150, 32672281643188909, 14373258012118246, 13114195683776624, 15313646224090191, 1878210540758546, 35658976429109825, 5334789801931269, 13697329022920645, 10803717771408750]

Alice decodes and reads: What are you talking about Alice? 

## Potential Improvements

If we had more time, we were considering upping the block size to something really large for added security, padding the remaning space with randomly generated characters.
