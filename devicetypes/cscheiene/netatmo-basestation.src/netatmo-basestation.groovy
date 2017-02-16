/**
 *  netatmo-basestation
 *
 *  Copyright 2014 Brian Steere
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * Based on Brian Steere's netatmo-basestation DTH
 */
metadata {
	definition (name: "Netatmo Basestation", namespace: "cscheiene", author: "Brian Steere,cscheiene") {
		capability "Relative Humidity Measurement"
		capability "Temperature Measurement"
        capability "Sensor"

		attribute "carbonDioxide", "string"
		attribute "noise", "string"
		attribute "pressure", "string"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles (scale: 2) {
		multiAttributeTile(name:"main", type:"generic", width:6, height:4) {
			tileAttribute("temperature", key: "PRIMARY_CONTROL") {
            	attributeState "temperature",label:'${currentValue}°', icon:"st.Weather.weather2", backgroundColors:[
                	[value: 32, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 92, color: "#d04e00"],
					[value: 98, color: "#bc2323"]
				]
            }
            tileAttribute ("humidity", key: "SECONDARY_CONTROL") {
				attributeState "humidity", label:'Humidity: ${currentValue}%'
			}
		} 
		valueTile("temperature", "device.temperature", width: 2, height: 2, canChangeIcon: false) {
 			state("temperature", label: '${currentValue}°', icon:"st.Weather.weather2", backgroundColors: [
 				[value: 31, color: "#153591"],
 				[value: 44, color: "#1e9cbb"],
 				[value: 59, color: "#90d2a7"],
 				[value: 74, color: "#44b621"],
 				[value: 84, color: "#f1d801"],
 				[value: 95, color: "#d04e00"],
 				[value: 96, color: "#bc2323"]
 				]
 				)
 		}
        		valueTile("min_temp", "min_temp", width: 2, height: 2) {
 			state "min_temp", label: 'Min: ${currentValue}°'
 		}
        		valueTile("max_temp", "max_temp", width: 2, height: 2) {
 			state "max_temp", label: 'Max: ${currentValue}°'
 		}        
 		valueTile("humidity", "device.humidity", inactiveLabel: false) {
 			state "humidity", label:'${currentValue}%', unit:"Humidity"
 		}
 		valueTile("carbonDioxide", "device.carbonDioxide", width: 2, height: 2, inactiveLabel: false) {
 			state "carbonDioxide", label:'${currentValue}ppm', unit:"CO2", backgroundColors: [
 				[value: 600, color: "#44B621"],
                [value: 999, color: "#ffcc00"],
                [value: 1000, color: "#e86d13"]
 				]
 		}
 		valueTile("noise", "device.noise", width: 2, height: 2, inactiveLabel: false) {
 			state "noise", label:'${currentValue}db', unit:"Noise"
 		}
 		valueTile("pressure", "device.pressure", width: 2, height: 2, inactiveLabel: false) {
 			state "pressure", label:'${currentValue}mbar', unit:"Pressure"
 		}
 		standardTile("refresh", "device.pressure", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
 			state "default", action:"device.poll", icon:"st.secondary.refresh"
 		}
 		main(["main"])
 		details(["main", "carbonDioxide", "noise", "pressure", "max_temp", "min_temp", "refresh"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'humidity' attribute
	// TODO: handle 'temperature' attribute
	// TODO: handle 'carbonDioxide' attribute
	// TODO: handle 'noise' attribute
	// TODO: handle 'pressure' attribute

}

def poll() {
	parent.poll()
}