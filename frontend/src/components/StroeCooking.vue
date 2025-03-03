<template>
    <v-card outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            Cooking # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            Cooking
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String label="Foodid" v-model="value.foodid" :editMode="editMode" :inputUI="''"/>
            <String label="Options" v-model="value.options" :editMode="editMode" :inputUI="''"/>
            <String label="Status" v-model="value.status" :editMode="editMode" :inputUI="''"/>
            <String label="Orderid" v-model="value.orderid" :editMode="editMode" :inputUI="''"/>
            <String label="Address" v-model="value.address" :editMode="editMode" :inputUI="''"/>
            <String label="Customerid" v-model="value.customerid" :editMode="editMode" :inputUI="''"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            <v-spacer></v-spacer>
            <div v-if="!editMode">
                <v-btn
                    color="primary"
                    text
                    @click="edit"
                >
                    수정
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                >
                    삭제
                </v-btn>
            </div>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                저장
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openAccept"
            >
                Accept
            </v-btn>
            <v-dialog v-model="acceptDiagram" width="500">
                <AcceptCommand
                    @closeDialog="closeAccept"
                    @accept="accept"
                ></AcceptCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openReject"
            >
                Reject
            </v-btn>
            <v-dialog v-model="rejectDiagram" width="500">
                <RejectCommand
                    @closeDialog="closeReject"
                    @reject="reject"
                ></RejectCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCookStart"
            >
                CookStart
            </v-btn>
            <v-dialog v-model="cookStartDiagram" width="500">
                <CookStartCommand
                    @closeDialog="closeCookStart"
                    @cookStart="cookStart"
                ></CookStartCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCookFinish"
            >
                CookFinish
            </v-btn>
            <v-dialog v-model="cookFinishDiagram" width="500">
                <CookFinishCommand
                    @closeDialog="closeCookFinish"
                    @cookFinish="cookFinish"
                ></CookFinishCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'StroeCooking',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            acceptDiagram: false,
            rejectDiagram: false,
            cookStartDiagram: false,
            cookFinishDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/cookings'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async accept(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['accept'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeAccept();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openAccept() {
                this.acceptDiagram = true;
            },
            closeAccept() {
                this.acceptDiagram = false;
            },
            async reject(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['reject'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeReject();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openReject() {
                this.rejectDiagram = true;
            },
            closeReject() {
                this.rejectDiagram = false;
            },
            async cookStart(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['cookstart'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeCookStart();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openCookStart() {
                this.cookStartDiagram = true;
            },
            closeCookStart() {
                this.cookStartDiagram = false;
            },
            async cookFinish(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['cookfinish'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeCookFinish();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openCookFinish() {
                this.cookFinishDiagram = true;
            },
            closeCookFinish() {
                this.cookFinishDiagram = false;
            },
        },
    }
</script>

